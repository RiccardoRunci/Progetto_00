package it.unicam.cs.mpgc.rpg120666.Controller;

import javafx.scene.input.KeyCode;
public class ManagerTastiera
{
    private MatriceGiocoController controller;

    public ManagerTastiera(MatriceGiocoController controller)
    {
        this.controller = controller;
    }

    public void gestisciTasto(KeyCode tasto)
    {
        switch (tasto)
        {
            case W -> controller.muoviSu();
            case S -> controller.muoviGiu();
            case A -> controller.muoviSinistra();
            case D -> controller.muoviDestra();
        }
    }
}
