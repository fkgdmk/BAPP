package services;

import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.Label;


/**
 * Created by Fredrik on 19-05-2017.
 */
public class MemberService
{

    public void setGroupPicker (JFXComboBox<Label> pickGroup)
    {

        pickGroup.getItems().add(new Label("Gruppe 1"));
        pickGroup.getItems().add(new Label("Gruppe 2"));
        pickGroup.getItems().add(new Label("Gruppe 3"));
    }



}
