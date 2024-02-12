package com.codeoftheweb.salvo.core.util;

import com.codeoftheweb.salvo.model.dto.ShipDto;

import java.util.*;
import java.util.stream.Collectors;

public class ShipValidation {

    public static final Map<String, Integer> shipTypesAndSizes;

    static {
        shipTypesAndSizes = new HashMap<>();
        shipTypesAndSizes.put("carrier", 5);
        shipTypesAndSizes.put("battleship", 4);
        shipTypesAndSizes.put("submarine", 3);
        shipTypesAndSizes.put("destroyer", 3);
        shipTypesAndSizes.put("patrol boat", 2);
    }

    public static void areShipTypesAndLocationsValid(List<ShipDto> shipDtoList) {
        List<String> shipTypesOfShipsDto = getShipTypesOfShipDtoList(shipDtoList);
        List<List<String>> shipLocationListOfShipDto = getShipLocationsListOfShipDto(shipDtoList);
        boolean isNumberOfShipsValid = shipDtoList.size() >= 1 && shipDtoList.size() <= 5;
        boolean areAllShipsUnique = areAllShipsUnique(shipTypesOfShipsDto);
        boolean doShipTypesHaveCorrectName = haveShipTypesCorrectName(shipTypesOfShipsDto);
        boolean doShipLocationsHaveCorrectSyntax = hasCorrectShipLocationsSyntax(shipLocationListOfShipDto);
        boolean doShipsHaveCorrectSize = hasCorrectShipSize(shipDtoList);
        boolean areShipLocationsInConsecutiveOrder = areShipLocationsConsecutive(shipLocationListOfShipDto);

        if (!isNumberOfShipsValid) {
            throw new IllegalArgumentException("The number of ships has to be between 1 and 5.");
        } else if (!areAllShipsUnique) {
            throw new IllegalArgumentException("Duplicated ships.");
        } else if (!doShipTypesHaveCorrectName) {
            throw new IllegalArgumentException("Wrong ship names.");
        } else if (!doShipLocationsHaveCorrectSyntax) {
            throw new IllegalArgumentException("Wrong Ship Location Syntax.");
        } else if (!doShipsHaveCorrectSize) {
            throw new IllegalArgumentException("Wrong ship size.");
        } else if (!areShipLocationsInConsecutiveOrder) {
            throw new IllegalArgumentException("Ships are not consecutive order.");
        }
    }

    public static boolean areAllShipsUnique(List<String> shipTypesList) {
        Set<String> shipTypesSet = new HashSet<>(shipTypesList);
        return shipTypesList.size() == shipTypesSet.size();
    }

    public static boolean haveShipTypesCorrectName(List<String> shipTypesList) {
        return shipTypesList.stream()
                .allMatch(shipType -> shipType != null && shipTypesAndSizes.containsKey(shipType.toLowerCase()));
    }

    public static boolean hasCorrectShipLocationsSyntax(List<List<String>> shipLocationsList) {
        return shipLocationsList.stream()
                .allMatch(shipLocations -> shipLocations != null && shipLocations.stream()
                        .allMatch(location -> {
                            String locationWithoutSpace = location.replaceAll("\\s", "");
                            return locationWithoutSpace.length() >= 2 &&
                                    isRowLetterValid(locationWithoutSpace.charAt(0)) &&
                                    isColNumberValid(locationWithoutSpace.substring(1));
                        }));
    }

    public static boolean isRowLetterValid(Character rowLetter) {
        return Character.isLetter(rowLetter) &&
                Character.toLowerCase(rowLetter) >= 'a' &&
                Character.toLowerCase(rowLetter) <= 'j';
    }

    public static boolean isColNumberValid(String colNumberAsString) {
        try {
            int colNumber = Integer.parseInt(colNumberAsString);
            return colNumber >= 1 && colNumber <= 10;
        } catch (NumberFormatException e) {
            return  false;
        }
    }

    public static boolean hasCorrectShipSize(List<ShipDto> shipDtoList) {
        return shipDtoList.stream()
                .allMatch(shipDto -> {
                    String shipType = shipDto.getShipType();
                    Integer shipLocationsSize = shipDto.getShipLocations().size();
                    return Objects.equals(shipTypesAndSizes.get(shipType), shipLocationsSize);
                });
    }

    public static boolean areShipLocationsConsecutive(List<List<String>> shipLocationsList) {
        return shipLocationsList.stream()
                .allMatch(shipLocations -> {
                    List<Character> rowLetters = getShipLocationsRowLetters(shipLocations);
                    List<Integer> colNumbers = getShipLocationsColNumbers(shipLocations);
                    return (areColNumbersConsecutive(colNumbers) && areAllRawLettersSame(rowLetters)) ||
                            (areRowLettersConsecutive(rowLetters) && areAllColNumbersSame(colNumbers));
                });
    }

    public static boolean areColNumbersConsecutive(List<Integer> colNumbers) {
        for (int i = 1; i < colNumbers.size(); i++) {
            if (colNumbers.get(i) != colNumbers.get(i - 1) + 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean areRowLettersConsecutive(List<Character> rowLetters) {
        for (int i = 1; i < rowLetters.size(); i++) {
            if(rowLetters.get(i) != rowLetters.get(i - 1) + 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean areAllColNumbersSame(List<Integer> colNumbers) {
        return colNumbers.stream().distinct().count() == 1;
    }

    public static boolean areAllRawLettersSame(List<Character> rowLetters) {
        return rowLetters.stream().distinct().count() <= 1;
    }

    public static List<String> getShipTypesOfShipDtoList(List<ShipDto> shipDtoList) {
        return shipDtoList.stream()
                .map(ShipDto::getShipType)
                .collect(Collectors.toList());
    }

    public static List<List<String>> getShipLocationsListOfShipDto(List<ShipDto> shipDtoList) {
        return shipDtoList.stream()
                .map(ShipDto::getShipLocations)
                .collect(Collectors.toList());
    }

    public static List<Character> getShipLocationsRowLetters(List<String> shipLocations) {
        return shipLocations.stream()
                .map(gridName -> gridName.charAt(0)).sorted().collect(Collectors.toList());
    }

    public static List<Integer> getShipLocationsColNumbers(List<String> shipLocations) {
        return shipLocations.stream()
                .map(gridName -> Integer.parseInt(gridName.substring(1))).sorted().collect(Collectors.toList());
    }
}
