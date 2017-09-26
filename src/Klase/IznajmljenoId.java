/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klase;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class IznajmljenoId implements Serializable{
    
    @ManyToOne
    private Clanovi c;
    
    @ManyToOne
    private Knjige k;

    /**
     * @return the c
     */
    public Clanovi getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(Clanovi c) {
        this.c = c;
    }

    /**
     * @return the k
     */
    public Knjige getK() {
        return k;
    }

    @Override
    public int hashCode() {
        int result;
        result = (c != null ? c.hashCode() : 0);
        result = 31 * result + (k != null ? k.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null|| getClass()!=obj.getClass()) return false;
        IznajmljenoId that=(IznajmljenoId) obj;
        
        if (c != null ? !c.equals(that.c) : that.c != null) return false;
        if (k != null ? !k.equals(that.k) : that.k != null)
            return false;

        return true;
    }

    /**
     * @param k the k to set
     */
    public void setK(Knjige k) {
        this.k = k;
    }
    public IznajmljenoId(){
        
    }
}
