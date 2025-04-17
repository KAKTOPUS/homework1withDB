package tools;
import data.AnimalTypeData;

public class AcceptableType {

    public boolean isAcceptableType(String str) {
            if (str.equals(AnimalTypeData.CAT.name())) {
                return true;
            }
            if (str.equals(AnimalTypeData.DOG.name())) {
                return true;
            }
            if (str.equals(AnimalTypeData.DUCK.name())) {
                return true;
            }
            return false;
        }

    private boolean acceptableType(String str) {
        for (AnimalTypeData animalTypeData : AnimalTypeData.values()) {
            if (str.equals(animalTypeData.name())) {
                return true;
            }
        }
        return false;
    }

    public AnimalTypeData setType(String str) {
        if (acceptableType(str)) {
            return AnimalTypeData.valueOf(str);
        }
        return null;
    }
}