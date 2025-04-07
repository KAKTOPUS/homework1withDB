package tools;
import data.AnimalTypeData;
public class AcceptableType {
    public boolean acceptableType(String str) {
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
}