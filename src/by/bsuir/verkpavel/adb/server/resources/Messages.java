package by.bsuir.verkpavel.adb.server.resources;

public enum Messages {
    DATE_AFTER_NOW("Дата позже текущей!"),
    DATE_BEFORE_MIN_DATE("Дата раньше минимальной!"),
    RUSSIAN_ALPHABET("йцукенгшщзхъфывапролджэячсмитьбюё"),
    CLIENT_SUCCESSFULLY_ADDED("Клиент успешно добавлен"),
    DUBLICATE_PASSPORT_SERIOS_OR_IDENTIFY_NUMBER("Дублирование номера и серии паспорта или индентификационного номера"),
    CLIENT_SUCCESSFULLY_UPDATED("Клиент успешно обновлен"),
    
    DUBLICATE_CONTRACT_NUMBER("Дублирование номера договора"),
    CONTRACT_SUCCESSFULLY_ADDED("Контракт успешно добавлен"), 
    DATE_AFTER_MAX_DATE("Дата позже максимальной"), 
    STARTDATEBEFORENOW_OR_ENDDATEBEFORESTART("Начальная даты раньше текущей или дата окончания раньше даты начала");
    
    
    private String value;
    private Messages(String value){
        this.value = value;
    }
    public String get(){
        return value;
    }
}
