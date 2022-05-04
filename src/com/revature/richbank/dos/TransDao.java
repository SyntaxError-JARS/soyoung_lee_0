package com.revature.richbank.dos;

public class TransDao implements Crudable {

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
