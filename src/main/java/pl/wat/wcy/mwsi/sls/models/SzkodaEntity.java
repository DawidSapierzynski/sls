package pl.wat.wcy.mwsi.sls.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "szkoda", schema = "sls")
public class SzkodaEntity {
    private long idSzkoda;
    private Timestamp dataOtworzenia;
    private String typUszkodzenia;
    private BigDecimal wycena;
    private String stan;
    private byte czyZamknieto;
    private byte czyAnulowano;
    private Timestamp dataZamkniecia;
    private Timestamp dataRozpoczeciaLikwidacji;
    private Timestamp dataZakonczeniaLikwidacji;
    private Collection<DokumentEntity> dokumentsByIdSzkoda;
    private Collection<PlatnoscEntity> platnoscsByIdSzkoda;
    private PolisaEntity polisa;
    private OsobaEntity poszkodowany;
    private OsobaEntity sprawca;
    private OsobaEntity likwidatorTechniczny;
    private OsobaEntity likwidatorMerytoryczny;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_Szkoda")
    public long getIdSzkoda() {
        return idSzkoda;
    }

    public void setIdSzkoda(long idSzkoda) {
        this.idSzkoda = idSzkoda;
    }

    @Basic
    @Column(name = "Data_Otworzenia")
    public Timestamp getDataOtworzenia() {
        return dataOtworzenia;
    }

    public void setDataOtworzenia(Timestamp dataOtworzenia) {
        this.dataOtworzenia = dataOtworzenia;
    }

    @Basic
    @Column(name = "Typ_Uszkodzenia")
    public String getTypUszkodzenia() {
        return typUszkodzenia;
    }

    public void setTypUszkodzenia(String typUszkodzenia) {
        this.typUszkodzenia = typUszkodzenia;
    }

    @Basic
    @Column(name = "Wycena")
    public BigDecimal getWycena() {
        return wycena;
    }

    public void setWycena(BigDecimal wycena) {
        this.wycena = wycena;
    }

    @Basic
    @Column(name = "Stan")
    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    @Basic
    @Column(name = "Czy_Zamknieto")
    public byte getCzyZamknieto() {
        return czyZamknieto;
    }

    public void setCzyZamknieto(byte czyZamknieto) {
        this.czyZamknieto = czyZamknieto;
    }

    @Basic
    @Column(name = "Czy_Anulowano")
    public byte getCzyAnulowano() {
        return czyAnulowano;
    }

    public void setCzyAnulowano(byte czyAnulowano) {
        this.czyAnulowano = czyAnulowano;
    }

    @Basic
    @Column(name = "Data_Zamkniecia")
    public Timestamp getDataZamkniecia() {
        return dataZamkniecia;
    }

    public void setDataZamkniecia(Timestamp dataZamkniecia) {
        this.dataZamkniecia = dataZamkniecia;
    }

    @Basic
    @Column(name = "Data_Rozpoczecia_Likwidacji")
    public Timestamp getDataRozpoczeciaLikwidacji() {
        return dataRozpoczeciaLikwidacji;
    }

    public void setDataRozpoczeciaLikwidacji(Timestamp dataRozpoczeciaLikwidacji) {
        this.dataRozpoczeciaLikwidacji = dataRozpoczeciaLikwidacji;
    }

    @Basic
    @Column(name = "Data_Zakonczenia_Likwidacji")
    public Timestamp getDataZakonczeniaLikwidacji() {
        return dataZakonczeniaLikwidacji;
    }

    public void setDataZakonczeniaLikwidacji(Timestamp dataZakonczeniaLikwidacji) {
        this.dataZakonczeniaLikwidacji = dataZakonczeniaLikwidacji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SzkodaEntity that = (SzkodaEntity) o;
        return idSzkoda == that.idSzkoda &&
                czyZamknieto == that.czyZamknieto &&
                czyAnulowano == that.czyAnulowano &&
                Objects.equals(dataOtworzenia, that.dataOtworzenia) &&
                Objects.equals(typUszkodzenia, that.typUszkodzenia) &&
                Objects.equals(wycena, that.wycena) &&
                Objects.equals(stan, that.stan) &&
                Objects.equals(dataZamkniecia, that.dataZamkniecia) &&
                Objects.equals(dataRozpoczeciaLikwidacji, that.dataRozpoczeciaLikwidacji) &&
                Objects.equals(dataZakonczeniaLikwidacji, that.dataZakonczeniaLikwidacji);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSzkoda, dataOtworzenia, typUszkodzenia, wycena, stan, czyZamknieto, czyAnulowano, dataZamkniecia, dataRozpoczeciaLikwidacji, dataZakonczeniaLikwidacji);
    }

    @OneToMany(mappedBy = "szkoda")
    public Collection<DokumentEntity> getDokumentsByIdSzkoda() {
        return dokumentsByIdSzkoda;
    }

    public void setDokumentsByIdSzkoda(Collection<DokumentEntity> dokumentsByIdSzkoda) {
        this.dokumentsByIdSzkoda = dokumentsByIdSzkoda;
    }

    @OneToMany(mappedBy = "szkoda")
    public Collection<PlatnoscEntity> getPlatnoscsByIdSzkoda() {
        return platnoscsByIdSzkoda;
    }

    public void setPlatnoscsByIdSzkoda(Collection<PlatnoscEntity> platnoscsByIdSzkoda) {
        this.platnoscsByIdSzkoda = platnoscsByIdSzkoda;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Polisa", referencedColumnName = "ID_Polisa", nullable = false)
    public PolisaEntity getPolisa() {
        return polisa;
    }

    public void setPolisa(PolisaEntity polisa) {
        this.polisa = polisa;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Poszkodowany", referencedColumnName = "ID_Osoba", nullable = false)
    public OsobaEntity getPoszkodowany() {
        return poszkodowany;
    }

    public void setPoszkodowany(OsobaEntity poszkodowany) {
        this.poszkodowany = poszkodowany;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Sprawca", referencedColumnName = "ID_Osoba")
    public OsobaEntity getSprawca() {
        return sprawca;
    }

    public void setSprawca(OsobaEntity sprawca) {
        this.sprawca = sprawca;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Likwidator_Techniczny", referencedColumnName = "ID_Osoba")
    public OsobaEntity getLikwidatorTechniczny() {
        return likwidatorTechniczny;
    }

    public void setLikwidatorTechniczny(OsobaEntity likwidatorTechniczny) {
        this.likwidatorTechniczny = likwidatorTechniczny;
    }

    @ManyToOne
    @JoinColumn(name = "ID_Likwidator_Merytoryczny", referencedColumnName = "ID_Osoba")
    public OsobaEntity getLikwidatorMerytoryczny() {
        return likwidatorMerytoryczny;
    }

    public void setLikwidatorMerytoryczny(OsobaEntity likwidatorMerytoryczny) {
        this.likwidatorMerytoryczny = likwidatorMerytoryczny;
    }
}
