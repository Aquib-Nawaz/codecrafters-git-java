package com.codecrafter.git.Objects;

import java.io.FileNotFoundException;
import java.util.*;

class TreeLeaf {
    public String mode,name,sha;
    int idx;

}
public class TreeObject extends GitObjects{
    byte [] type = {'t', 'r', 'e', 'e'};

    public TreeObject(String repo, String hash) throws FileNotFoundException {
        super(repo, hash);
    }

    private TreeLeaf readTreeLeaf(int idx){
        int valueLen=0;
        TreeLeaf ret = new TreeLeaf();

        if(fileContent[idx+5]==' '){
            valueLen = 5;
        }
        else if(fileContent[idx+6]==' ') {
            valueLen = 6;
        }
        ret.mode = new String(fileContent, idx, valueLen);
        idx += valueLen+1;

        valueLen = 0;
        while(fileContent[idx+valueLen]!=0)
            valueLen++;
        ret.name = new String(fileContent, idx, valueLen);
        idx += valueLen+1;

        valueLen = 20;
        ret.sha = new String(fileContent, idx, valueLen);
        idx += valueLen;

        ret.idx = idx;
        return ret;
    }

    @Override
    public void readObject() {
        super.readObject(type);
//        [mode name020]
        int cur = 0;
        Set<String> names = new TreeSet<>();
        while(cur!=fileContent.length) {
            TreeLeaf ret = readTreeLeaf(cur);
            cur = ret.idx;
            names.add(ret.name);
        }

        for(String name:names)
            System.out.println(name);
    }

    @Override
    public String writeObject(String filename) {
        return null;
    }
}