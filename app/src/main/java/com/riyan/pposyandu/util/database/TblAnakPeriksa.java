package com.riyan.pposyandu.util.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class TblAnakPeriksa {
    @Id(autoincrement = true)
    private Long id;

    private Long idAnak;
    private String Bb;
    private String Tb;
    private String Bulan;


    @Generated(hash = 658181366)
    public TblAnakPeriksa(Long id, Long idAnak, String Bb, String Tb, String Bulan) {
        this.id = id;
        this.idAnak = idAnak;
        this.Bb = Bb;
        this.Tb = Tb;
        this.Bulan = Bulan;
    }

    @Generated(hash = 928739849)
    public TblAnakPeriksa() {
    }

    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAnak() {
        return idAnak;
    }

    public void setIdAnak(Long idAnak) {
        this.idAnak = idAnak;
    }

    public String getBb() {
        return this.Bb;
    }


    public void setBb(String Bb) {
        this.Bb = Bb;
    }


    public String getTb() {
        return this.Tb;
    }


    public void setTb(String Tb) {
        this.Tb = Tb;
    }


    public String getBulan() {
        return this.Bulan;
    }


    public void setBulan(String Bulan) {
        this.Bulan = Bulan;
    }

}
