/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klase;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "iznajmljeno")
@AssociationOverrides({
    @AssociationOverride(name = "pk.c",
            joinColumns = @JoinColumn(name = "br_licne_karte")),
    @AssociationOverride(name = "pk.k",
            joinColumns = @JoinColumn(name = "knjiga_id"))})
public class Iznajmljeno {

    @EmbeddedId
    private IznajmljenoId pk = new IznajmljenoId();

    @Column(name = "datum")
    private String datum = LocalDate.now().toString();

    public Iznajmljeno() {

    }

    public Iznajmljeno(Clanovi c, Knjige k) {
        this.setClanovi(c);
        this.setKnjige(k);

    }

    public IznajmljenoId getPk() {
        return pk;
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(IznajmljenoId pk) {
        this.pk = pk;
    }

    /**
     * @return the datum
     */
    public String getDatum() {
        return datum;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(String datum) {
        this.datum = datum;
    }

    @Transient
    public Clanovi getClanovi() {
        return getPk().getC();
    }

    public void setClanovi(Clanovi clan) {
        getPk().setC(clan);
    }

    @Transient
    public Knjige getKnjige() {
        return getPk().getK();
    }

    public void setKnjige(Knjige knjiga) {
        getPk().setK(knjiga);
    }

    @Override
    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Iznajmljeno that = (Iznajmljeno) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "(" + pk.getC().getBrLicneKarte() + ")" + " - " + pk.getK().getNaslov() + " (" + pk.getK().getAutor() + ")" + " datum: " + datum;
    }

}
