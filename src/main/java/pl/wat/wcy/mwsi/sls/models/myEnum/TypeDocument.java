package pl.wat.wcy.mwsi.sls.models.myEnum;

public enum TypeDocument {
    AKCEPTACJAWYCENY("Akceptacja wyceny"),
    ZGLOSZENIESZKODY("Zgloszenie szkody"),
    WYCENA("Wycena"),
    ANULOWANIEWYCENY("Anulowanie wyceny");

    private String type;

    TypeDocument(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
