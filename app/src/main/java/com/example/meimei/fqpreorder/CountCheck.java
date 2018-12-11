package com.example.meimei.fqpreorder;

public class CountCheck {

    private final int PREORDER_LIMIT;
    private int database_count;

    public CountCheck(String count){
        PREORDER_LIMIT = Integer.parseInt(count);
    }

    private void retrieval(){
        //TODO retrieve database_count from database
        database_count = 0;
    }

    public Boolean stopPreorder() {
        retrieval();
        if (database_count >= PREORDER_LIMIT){
            return true;
        }
        return false;
    }

    public int getCurrent(){
        retrieval();
        return database_count;
    }

}
