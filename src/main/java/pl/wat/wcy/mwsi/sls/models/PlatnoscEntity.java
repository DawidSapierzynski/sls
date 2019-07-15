package pl.wat.wcy.mwsi.sls.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "platnosc", schema = "sls")
class PlatnoscEntity {
    private long idPlatnosc;
    private String status;
    private String typ;
    private Timestamp dataUtworzenia;
    private String zrodlo;
    private Timestamp dataKsiegowania;
    private Timestamp dataPlatnosci;
    private BigDecimal kwota;
    private String waluta;
    private DokumentEntity dokument;
    private OsobaEntity osoba;
    private SzkodaEntity szkoda;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_Platnosc")
    public long getIdPlatnosc() {
        return idPlatnosc;
    }

    public void setIdPlatnosc(long idPlatnosc) {
        this.idPlatnosc = idPlatnosc;
    }

    @Basic
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "Typ")
    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    @Basic
    @Column(name = "Data_Utworzenia")
    public Timestamp getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(Timestamp dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    @Basic
    @Column(name = "Zrodlo")
    public String getZrodlo() {
        return zrodlo;
    }

    public void setZrodlo(String zrodlo) {
        this.zrodlo = zrodlo;
    }

    @Basic
    @Column(name = "Data_Ksiegowania")
    public Timestamp getDataKsiegowania() {
        return dataKsiegowania;
    }

    public void setDataKsiegowania(Timestamp dataKsiegowania) {
        this.dataKsiegowania = dataKsiegowania;
    }

    @Basic
    @Column(name = "Data_Platnosci")
    public Timestamp getDataPlatnosci() {
        return dataPlatnosci;
    }

    public void setDataPlatnosci(Timestamp dataPlatnosci) {
        this.dataPlatnosci = dataPlatnosci;
    }

    @Basic
    @Column(name = "Kwota")
    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    @Basic
    @Column(name = "Waluta")
    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlatnoscEntity that = (PlatnoscEntity) o;
        return idPlatnosc == that.idPlatnosc &&
                Objects.equals(status, that.status) &&
                Objects.equals(typ, that.typ) &&
                Objects.equals(dataUtworzenia, that.dataUtworzenia) &&
                Objects.equals(zrodlo, that.zrodlo) &&
                Objects.equals(dataKsiegowania, that.dataKsiegowania) &&
                Objects.equals(dataPlatnosci, that.dataPlatnosci) &&
                Objects.equals(kwota, that.kwota) &&
                Objects.equals(waluta, that.waluta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlatnosc, status, typ, dataUtworzenia, zrodlo, dataKsiegowania, dataPlatnosci, kwota, waluta);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Dokument", referencedColumnName = "ID_Dokument", nullable = false)
    public DokumentEntity getDokument() {
        return dokument;
    }

    public void setDokument(DokumentEntity dokument) {
        this.dokument = dokument;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Osoba", referencedColumnName = "ID_Osoba", nullable = false)
    public OsobaEntity getOsoba() {
        return osoba;
    }

    public void setOsoba(OsobaEntity osoba) {
        this.osoba = osoba;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Szkoda", referencedColumnName = "ID_Szkoda", nullable = false)
    public SzkodaEntity getSzkoda() {
        return szkoda;
    }

    public void setSzkoda(SzkodaEntity szkoda) {
        this.szkoda = szkoda;
    }
}
