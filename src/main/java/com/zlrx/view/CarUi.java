package com.zlrx.view;

import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.zlrx.domain.Car;
import com.zlrx.service.CarService;
import com.zlrx.util.DateToLocalDateConverter;
import com.zlrx.util.StringToLocalDateConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@SpringUI
@Theme("valo")
public class CarUi extends UI {

    private static final String DATE_FORMAT = "yyyy.MM.dd";

    private static final StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter(DATE_FORMAT);

    private static final DateToLocalDateConverter dateToLocalDateConverter = new DateToLocalDateConverter();

    @Autowired
    private CarService carService;

    private Grid grid;

    @Override
    protected void init(final VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(carGrid());
        setContent(layout);
    }

    private Grid carGrid() {
        final BeanItemContainer<Car> carBeanItemContainer = new BeanItemContainer<>(Car.class, carService.findAll());
        grid = new Grid(carBeanItemContainer);
        grid.setSizeFull();
        grid.removeColumn("id");

        final Grid.HeaderRow headerRow = grid.getDefaultHeaderRow();
        headerRow.getCell("manufacturer").setText("Márka");
        headerRow.getCell("type").setText("Típus");
        headerRow.getCell("licencePlate").setText("Rendszám");
        headerRow.getCell("color").setText("Szín");
        headerRow.getCell("capacity").setText("Hengerűrtartalom");
        headerRow.getCell("dateOfManufacture").setText("Gyártás dátuma");

        grid.setColumnOrder("manufacturer", "type", "color", "capacity", "dateOfManufacture", "licencePlate");
        grid.setEditorEnabled(true);
        grid.getEditorFieldGroup().addCommitHandler(saveAction());

        addButtonToCarGridHeader();
        addDateEditorToDateOfManufacture();

        return grid;
    }

    private void addDateEditorToDateOfManufacture() {
        PopupDateField dateOfManufactureEditor = new PopupDateField();
        dateOfManufactureEditor.setConverter(dateToLocalDateConverter); //Vaadin 8-tól kidobható, a Java 8 új dátum idő apiját fogja használja
        grid.getColumn("dateOfManufacture").setConverter(stringToLocalDateConverter).setEditorField(dateOfManufactureEditor);
    }

    private void addButtonToCarGridHeader() {
        Grid.HeaderRow extraHeader = grid.prependHeaderRow();
        extraHeader.join("manufacturer", "type", "color", "capacity", "dateOfManufacture", "licencePlate");
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addComponents(newCarButton());
        extraHeader.getCell("manufacturer").setComponent(buttonLayout);
    }

    private Button newCarButton() {
        Button button = new Button();
        button.setIcon(FontAwesome.PLUS);
        button.setDescription("Új sor hozzáadása");
        button.addClickListener(event -> {
            Car car = new Car();
            car.setManufacturer("");
            car.setType("");
            car.setColor("");
            car.setLicencePlate("");
            car.setCapacity(999);
            car.setDateOfManufacture(LocalDate.now());
            grid.getContainerDataSource().addItem(car);
            grid.editItem(car);
        });
        return button;
    }

    private FieldGroup.CommitHandler saveAction() {
        return new FieldGroup.CommitHandler() {
            @Override
            public void preCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {

            }

            @Override
            public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
                BeanItem<Car> bi = (BeanItem<Car>) commitEvent.getFieldBinder().getItemDataSource();
                carService.save(bi.getBean());
            }
        };
    }

}
