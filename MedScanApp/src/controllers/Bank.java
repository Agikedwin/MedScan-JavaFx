/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.solutions.entorno.utilities.TableViewRenderer;
import static controllers.XRayServices.getAllXRay;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import models.BanksModel;

/**
 *
 * @author young
 */
public class Bank {

    private int bankId;
    private String accountNo;
    private String bankName;
    private boolean status;
    private Timestamp lastUpdated;
    private boolean deleted;
    private static BanksModel mBanks;

    public Bank() {
        mBanks = new BanksModel();
    }

    public boolean save() {
        return mBanks.insertBanks(this);
    }

    public static ArrayList fetchBanks() {
        return mBanks.getAllBanks();
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
   
    public  static TableView banksTV() {

        TableView tv;
        String[] headers = {"BANK ID","BANK NAME", "ACOOUNT NUMBER"};
        String[] property = {"bankId", "bankName","accountNo"};
        ArrayList<Object> model = fetchBanks();

        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

}
