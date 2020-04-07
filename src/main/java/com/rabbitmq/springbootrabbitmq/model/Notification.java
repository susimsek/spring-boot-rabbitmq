package com.rabbitmq.springbootrabbitmq.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//bu datayı kuyrukta döndereceğimiz için Serializabledan implemente aldık
@Data
public class Notification implements Serializable {
    
    private String id;
    private Date creadtedAt;
    private boolean seen;
    private String message;
}
