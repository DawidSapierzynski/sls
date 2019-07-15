package pl.wat.wcy.mwsi.sls.models.myEnum;

public enum Role {
    LTECHNICAL("LTECH"),
    LSUBSTANTIVE("LMECH"),
    WADMINISTRATIVE("PADMIN"),
    DIRECTOR("DYREKTOR"),
    CUSTOMER("KLIENT");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
