package com.shopapotheke.githubservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Repository {
    public int total_count;
    public boolean incomplete_results;
    public ArrayList<Item> items;
}
