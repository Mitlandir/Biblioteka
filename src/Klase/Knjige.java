/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klase;

import java.io.Serializable;
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
@Table(name="knjige")
public class Knjige implements Serializable{
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "knjiga_id")
    private int id;
    
    @Column(name = "naslov")
    private String naslov;
    
    @Column(name = "autor")
    private String autor;
    
    @Column(name = "kolicina")
    private int kolicina;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.k")
    private Set<Iznajmljeno> iznajmljeno=new HashSet<>();
    
    
    public Knjige(){
        
    }
    public Knjige(String naslov,String autor,int kolicina){
        this.naslov=naslov;
        this.autor=autor;
        this.kolicina=kolicina;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNaslov() {
        return naslov;
    }

    
    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getAutor() {
        return autor;
    }

  
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getKolicina() {
        return kolicina;
    }

    /**
     * @param kolicina the kolicina to set
     */
    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    
    public String toString(){
        return naslov+"("+autor+") kolicina: "+kolicina;
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
