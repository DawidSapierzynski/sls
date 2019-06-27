package pl.wat.wcy.mwsi.sls.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "dokument", schema = "sls")
public class DokumentEntity {
    private long idDokument;
    private String typ;
    private String zawartosc;
    private Timestamp dataUtworzenia;
    private OsobaEntity osoba;
    private SzkodaEntity szkoda;
    private Collection<PlatnoscEntity> platnoscsByIdDokument;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_Dokument")
    public long getIdDokument() {
        return idDokument;
    }

    public void setIdDokument(long idDokument) {
        this.idDokument = idDokument;
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
    @Column(name = "Zawartosc")
    public String getZawartosc() {
        return zawartosc;
    }

    public void setZawartosc(String zawartosc) {
        this.zawartosc = zawartosc;
    }

    @Basic
    @Column(name = "Data_Utworzenia")
    public Timestamp getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(Timestamp dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DokumentEntity that = (DokumentEntity) o;
        return idDokument == that.idDokument &&
                Objects.equals(typ, that.typ) &&
                Objects.equals(zawartosc, that.zawartosc) &&
                Objects.equals(dataUtworzenia, that.dataUtworzenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDokument, typ, zawartosc, dataUtworzenia);
    }

    @ManyToOne
    @JoinColumn(name = "ID_Osoba", referencedColumnName = "ID_Osoba")
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

    @OneToMany(mappedBy = "dokument")
    public Collection<PlatnoscEntity> getPlatnoscsByIdDokument() {
        return platnoscsByIdDokument;
    }

    public void setPlatnoscsByIdDokument(Collection<PlatnoscEntity> platnoscsByIdDokument) {
        this.platnoscsByIdDokument = platnoscsByIdDokument;
    }
}
