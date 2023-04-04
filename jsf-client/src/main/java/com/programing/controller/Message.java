package com.programing.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@ApplicationScoped
public class Message {

    public void sendMsg(FacesMessage.Severity facesMessage, String detalle) {
        FacesContext.getCurrentInstance()
                .addMessage(
                        null,
                        new FacesMessage(
                                facesMessage,
                                "Aviso",
                                detalle)
                );
    }

}
