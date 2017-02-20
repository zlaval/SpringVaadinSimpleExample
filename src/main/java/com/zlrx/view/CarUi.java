package com.zlrx.view;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.zlrx.domain.Car;
import com.zlrx.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class CarUi extends UI {

    @Autowired
    private CarRepository carRepository;


    @Override
    protected void init(final VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(carGrid());

        setContent(layout);
    }

    private Grid carGrid() {
        final BeanItemContainer<Car> carBeanItemContainer = new BeanItemContainer<>(Car.class, carRepository.findAll());
        final Grid grid = new Grid(carBeanItemContainer);
        grid.setSizeFull();
        return grid;
    }


}
