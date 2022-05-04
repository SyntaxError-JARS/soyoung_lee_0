package com.revature.richbank.dos;

import com.revature.richbank.models.Trans;

import java.io.IOException;

public class TransDao implements Crudable<Trans> {

    @Override
    public Trans create(Trans newTrans) {
        return null;
    }

    @Override
    public Trans[] findAll() throws IOException {
        return new Trans[0];
    }

    @Override
    public Trans findById(String id) {
        return null;
    }

    @Override
    public boolean update(Trans updateTrans) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
