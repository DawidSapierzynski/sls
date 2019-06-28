package pl.wat.wcy.mwsi.sls.models.myEnum;

public enum ConditionDamage {
    ZGLOSZONO("Zgloszono"),
    ZAKONCZONO("Zakonczono"),
    WYCENIONO("Wyceniono"),
    ANULOWANO("Anulowano");

    private final String condition;

    ConditionDamage(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }
}
