package bullsAndCows.model;

public enum GameResultEnum {
    YES("Да"), NO("Нет");

    private String translation;

    GameResultEnum(String translation){
        this.translation = translation;
    }

    @Override
    public String toString() {
        return translation;
    }
}
