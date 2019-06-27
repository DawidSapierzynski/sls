package pl.wat.wcy.mwsi.sls.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "polisa", schema = "sls")
public class PolisaEntity {
    private long idPolisa;
    private String numer;
    private String nazwa;
    private Timestamp dataRozpoczecia;
    private Timestamp dataZakonczenia;
    private OsobaEntity osoba;
    private Collection<SzkodaEntity> szkodasByIdPolisa;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_Polisa")
    public long getIdPolisa() {
        return idPolisa;
    }

    public void setIdPolisa(long idPolisa) {
        this.idPolisa = idPolisa;
    }

    @Basic
    @Column(name = "Numer")
    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    @Basic
    @Column(name = "Nazwa")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "Data_Rozpoczecia")
    public Timestamp getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Timestamp dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    @Basic
    @Column(name = "Data_Zakonczenia")
    public Timestamp getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Timestamp dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolisaEntity that = (PolisaEntity) o;
        return idPolisa == that.idPolisa &&
                Objects.equals(numer, that.numer) &&
                Objects.equals(nazwa, that.nazwa) &&
                Objects.equals(dataRozpoczecia, that.dataRozpoczecia) &&
                Objects.equals(dataZakonczenia, that.dataZakonczenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPolisa, numer, nazwa, dataRozpoczecia, dataZakonczenia);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Osoba", referencedColumnName = "ID_Osoba", nullable = false)
    public OsobaEntity getOsoba() {
        return osoba;
    }

    public void setOsoba(OsobaEntity osoba) {
        this.osoba = osoba;
    }

    @OneToMany(mappedBy = "polisa")
    public Collection<SzkodaEntity> getSzkodasByIdPolisa() {
        return szkodasByIdPolisa;
    }

    public void setSzkodasByIdPolisa(Collection<SzkodaEntity> szkodasByIdPolisa) {
        this.szkodasByIdPolisa = szkodasByIdPolisa;
    }
}
