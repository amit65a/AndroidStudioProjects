package com.example.demothird_custom_Array_Adaptor;

public class animallist {
    String animal_name;
    int animal_img;
    public animallist(int animal_img,String animal_name){
        this.animal_img=animal_img;
        this.animal_name=animal_name;
    }
    public int getAnimal_img(){
        return animal_img;
    }
    public String getAnimal_name(){
        return animal_name;
    }

}
