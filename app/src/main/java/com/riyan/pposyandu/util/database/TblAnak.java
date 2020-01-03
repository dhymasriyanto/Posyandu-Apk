package com.riyan.pposyandu.util.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class TblAnak {
    @Id(autoincrement = true)
    private Long id;

    private String nama;
    private String umur;
    private String bb;
    private String tb;
    private String bulan;

    @Generated(hash = 1445284314)
    public TblAnak(Long id, String nama, String umur, String bb, String tb, String bulan) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.bb = bb;
        this.tb = tb;
        this.bulan = bulan;
    }

    @Generated(hash = 735779047)
    public TblAnak() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmur() {
        return this.umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getBb() {
        return this.bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getTb() {
        return this.tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }

    public String getBulan() {
        return this.bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }
}
