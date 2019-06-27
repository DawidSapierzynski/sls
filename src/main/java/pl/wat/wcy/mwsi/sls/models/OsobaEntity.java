package pl.wat.wcy.mwsi.sls.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "osoba", schema = "sls")
public class OsobaEntity {
    private long idOsoba;
    private String login;
    private String imie;
    private String imie2;
    private String nazwisko;
    private Date dataUrodzenia;
    private String plec;
    private int narodowosc;
    private String pesel;
    private String rola;
    private String mail;
    private String telefon;
    private String haslo;
    private String nrKonta;
    private byte stan;
    private Collection<DokumentEntity> dokumentsByIdOsoba;
    private Collection<PlatnoscEntity> platnoscsByIdOsoba;
    private Collection<PolisaEntity> polisasByIdOsoba;
    private Collection<SzkodaEntity> szkodasByIdOsoba;
    private Collection<SzkodaEntity> szkodasByIdOsoba_0;
    private Collection<SzkodaEntity> szkodasByIdOsoba_1;
    private Collection<SzkodaEntity> szkodasByIdOsoba_2;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_Osoba")
    public long getIdOsoba() {
        return idOsoba;
    }

    public void setIdOsoba(long idOsoba) {
        this.idOsoba = idOsoba;
    }

    @Basic
    @Column(name = "Login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "Imie")
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "Imie2")
    public String getImie2() {
        return imie2;
    }

    public void setImie2(String imie2) {
        this.imie2 = imie2;
    }

    @Basic
    @Column(name = "Nazwisko")
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "Data_Urodzenia")
    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    @Basic
    @Column(name = "Plec")
    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    @Basic
    @Column(name = "Narodowosc")
    public int getNarodowosc() {
        return narodowosc;
    }

    public void setNarodowosc(int narodowosc) {
        this.narodowosc = narodowosc;
    }

    @Basic
    @Column(name = "PESEL")
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Basic
    @Column(name = "Rola")
    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    @Basic
    @Column(name = "Mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "Telefon")
    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Basic
    @Column(name = "Haslo")
    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Basic
    @Column(name = "Nr_Konta")
    public String getNrKonta() {
        return nrKonta;
    }

    public void setNrKonta(String nrKonta) {
        this.nrKonta = nrKonta;
    }

    @Basic
    @Column(name = "Stan")
    public byte getStan() {
        return stan;
    }

    public void setStan(byte stan) {
        this.stan = stan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OsobaEntity that = (OsobaEntity) o;
        return idOsoba == that.idOsoba &&
                narodowosc == that.narodowosc &&
                stan == that.stan &&
                Objects.equals(login, that.login) &&
                Objects.equals(imie, that.imie) &&
                Objects.equals(imie2, that.imie2) &&
                Objects.equals(nazwisko, that.nazwisko) &&
                Objects.equals(dataUrodzenia, that.dataUrodzenia) &&
                Objects.equals(plec, that.plec) &&
                Objects.equals(pesel, that.pesel) &&
                Objects.equals(rola, that.rola) &&
                Objects.equals(mail, that.mail) &&
                Objects.equals(telefon, that.telefon) &&
                Objects.equals(haslo, that.haslo) &&
                Objects.equals(nrKonta, that.nrKonta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOsoba, login, imie, imie2, nazwisko, dataUrodzenia, plec, narodowosc, pesel, rola, mail, telefon, haslo, nrKonta, stan);
    }

    @OneToMany(mappedBy = "osoba")
    public Collection<DokumentEntity> getDokumentsByIdOsoba() {
        return dokumentsByIdOsoba;
    }

    public void setDokumentsByIdOsoba(Collection<DokumentEntity> dokumentsByIdOsoba) {
        this.dokumentsByIdOsoba = dokumentsByIdOsoba;
    }

    @OneToMany(mappedBy = "osoba")
    public Collection<PlatnoscEntity> getPlatnoscsByIdOsoba() {
        return platnoscsByIdOsoba;
    }

    public void setPlatnoscsByIdOsoba(Collection<PlatnoscEntity> platnoscsByIdOsoba) {
        this.platnoscsByIdOsoba = platnoscsByIdOsoba;
    }

    @OneToMany(mappedBy = "osoba")
    public Collection<PolisaEntity> getPolisasByIdOsoba() {
        return polisasByIdOsoba;
    }

    public void setPolisasByIdOsoba(Collection<PolisaEntity> polisasByIdOsoba) {
        this.polisasByIdOsoba = polisasByIdOsoba;
    }

    @OneToMany(mappedBy = "poszkodowany")
    public Collection<SzkodaEntity> getSzkodasByIdOsoba() {
        return szkodasByIdOsoba;
    }

    public void setSzkodasByIdOsoba(Collection<SzkodaEntity> szkodasByIdOsoba) {
        this.szkodasByIdOsoba = szkodasByIdOsoba;
    }

    @OneToMany(mappedBy = "sprawca")
    public Collection<SzkodaEntity> getSzkodasByIdOsoba_0() {
        return szkodasByIdOsoba_0;
    }

    public void setSzkodasByIdOsoba_0(Collection<SzkodaEntity> szkodasByIdOsoba_0) {
        this.szkodasByIdOsoba_0 = szkodasByIdOsoba_0;
    }

    @OneToMany(mappedBy = "likwidatorTechniczny")
    public Collection<SzkodaEntity> getSzkodasByIdOsoba_1() {
        return szkodasByIdOsoba_1;
    }

    public void setSzkodasByIdOsoba_1(Collection<SzkodaEntity> szkodasByIdOsoba_1) {
        this.szkodasByIdOsoba_1 = szkodasByIdOsoba_1;
    }

    @OneToMany(mappedBy = "likwidatorMerytoryczny")
    public Collection<SzkodaEntity> getSzkodasByIdOsoba_2() {
        return szkodasByIdOsoba_2;
    }

    public void setSzkodasByIdOsoba_2(Collection<SzkodaEntity> szkodasByIdOsoba_2) {
        this.szkodasByIdOsoba_2 = szkodasByIdOsoba_2;
    }
}
