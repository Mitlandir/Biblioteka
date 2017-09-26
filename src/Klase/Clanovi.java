/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klase;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="clanovi")
public class Clanovi implements Serializable{
    
    @Id
    
    @Column(name = "br_licne_karte")
    private String brLicneKarte;
    
    @Column(name = "ime_i_prezime")
    private String imePrezime;
    
    @Column(name = "clan_od")
    private String clanOd=LocalDate.now().toString();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.c")
    private Set<Iznajmljeno> iznajmljeno=new HashSet<>();
    
    public Clanovi(){
       
    }
    public Clanovi(String brLicneKarte,String imePrezime){
        this.brLicneKarte=brLicneKarte;
        this.imePrezime=imePrezime;
    }

    public String getBrLicneKarte() {
        return brLicneKarte;
    }

 
    public void setBrLicneKarte(String brLicneKarte) {
        this.brLicneKarte = brLicneKarte;
    }

    
    public String getImePrezime() {
        return imePrezime;
    }

    
    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
        
    }
   
    

    /**
     * @return the clanOd
     */
    public String getClanOd() {
        return clanOd;
    }

    /**
     * @param clanOd the clanOd to set
     */
    public void setClanOd(String clanOd) {
        this.clanOd = clanOd;
    }
    public String toString(){
        return imePrezime+" ("+brLicneKarte+") clan od: "+clanOd;
    }

    /**
     * @return the iznajmljeno
     */
    public Set<Iznajmljeno> getIznajmljeno() {
        return iznajmljeno;
    }

    /**
     * @param iznajmljeno the iznajmljeno to set
     */
    public void setIznajmljeno(Set<Iznajmljeno> iznajmljeno) {
        this.iznajmljeno = iznajmljeno;
    }
}
