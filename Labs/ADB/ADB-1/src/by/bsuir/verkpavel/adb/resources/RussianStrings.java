package by.bsuir.verkpavel.adb.resources;

public enum RussianStrings {
    DATE_AFTER_NOW("Дата позже текущей!"),
    DATE_BEFORE_01011900("Дата раньше 01.01.1900!"),
    RUSSIAN_ALPHABET("йцукенгшщзхъфывапролджэячсмитьбю"),
    CLIENT_SUCCESSFULLY_ADDED("Клиент успешно добавлен"),
    DUBLICATE_PASSPORT_SERIOS_OR_IDENTIFY_NUMBER("Клиент с таким номером паспорта и серией или идентифиционым номером уже существует"),
    CLIENT_SUCCESSFULLY_UPDATED("Клиент успешно обновлен");
    
    
    private String value;
    private RussianStrings(String value){
        this.value = value;
    }
    public String get(){
        return value;
    }
}
