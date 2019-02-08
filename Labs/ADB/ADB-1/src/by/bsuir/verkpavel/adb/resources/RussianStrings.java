package by.bsuir.verkpavel.adb.resources;

public enum RussianStrings {
    DATE_AFTER_NOW("���� ����� �������!"),
    DATE_BEFORE_01011900("���� ������ 01.01.1900!"),
    RUSSIAN_ALPHABET("��������������������������������"),
    CLIENT_SUCCESSFULLY_ADDED("������ ������� ��������"),
    DUBLICATE_PASSPORT_SERIOS_OR_IDENTIFY_NUMBER("������ � ����� ������� �������� � ������ ��� �������������� ������� ��� ����������"),
    CLIENT_SUCCESSFULLY_UPDATED("������ ������� ��������");
    
    
    private String value;
    private RussianStrings(String value){
        this.value = value;
    }
    public String get(){
        return value;
    }
}
