package com.star.conversionfrom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Spinner unitTypeSpinner;
    private EditText amountEditText;

    private TextView
            teaspoonTextView, cupTextView, tablespoonTextView, ounceTextView, kilogramTextView,
            quartTextView, gallonTextView, poundTextView, milliliterTextView, literTextView,
            milligramTextView, pintTextView;

    private String itemSelectedInSpinner = "teaspoon";
    private double doubleToConvert = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemsToUnitTypeSpinner();

        addListenerToUnitTypeSpinner();
        addListenerToAmountEditText();

        amountEditText = (EditText) findViewById(R.id.amount_edit_text);

        initializeTextViews();

    }

    private void addItemsToUnitTypeSpinner() {

        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.conversion_types, android.R.layout.simple_spinner_item
        );

        unitTypeSpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);
    }

    private void addListenerToUnitTypeSpinner() {

        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSelectedInSpinner = (String) parent.getItemAtPosition(position);

                convertingFrom(itemSelectedInSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void addListenerToAmountEditText() {

        amountEditText = (EditText) findViewById(R.id.amount_edit_text);

        amountEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertingFrom(itemSelectedInSpinner);
            }
        });
    }

    private void initializeTextViews() {

        teaspoonTextView = (TextView) findViewById(R.id.tsp_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        tablespoonTextView = (TextView) findViewById(R.id.tbs_text_view);
        ounceTextView = (TextView) findViewById(R.id.oz_text_view);
        kilogramTextView = (TextView) findViewById(R.id.kg_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        poundTextView = (TextView) findViewById(R.id.pound_text_view);
        milliliterTextView = (TextView) findViewById(R.id.ml_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        milligramTextView = (TextView) findViewById(R.id.mg_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);

    }

    private void convertingFrom(String currentUnit) {

        if (currentUnit.equals("teaspoon")) {
            updateUnitType(Quantity.Unit.tsp);
        } else if (currentUnit.equals("cup")) {
            updateUnitType(Quantity.Unit.cup);
        } else if (currentUnit.equals("tablespoon")) {
            updateUnitType(Quantity.Unit.tbs);
        } else if (currentUnit.equals("ounce")) {
            updateUnitType(Quantity.Unit.oz);
        } else if (currentUnit.equals("kilogram")) {
            updateUnitType(Quantity.Unit.kg);
        } else if (currentUnit.equals("quart")) {
            updateUnitType(Quantity.Unit.quart);
        } else if (currentUnit.equals("gallon")) {
            updateUnitType(Quantity.Unit.gallon);
        } else if (currentUnit.equals("pound")) {
            updateUnitType(Quantity.Unit.pound);
        } else if (currentUnit.equals("milliliter")) {
            updateUnitType(Quantity.Unit.ml);
        } else if (currentUnit.equals("liter")) {
            updateUnitType(Quantity.Unit.liter);
        } else if (currentUnit.equals("milligram")) {
            updateUnitType(Quantity.Unit.mg);
        } else if (currentUnit.equals("pint")) {
            updateUnitType(Quantity.Unit.pint);
        }

    }

    private void updateUnitType(Quantity.Unit currentUnit) {

        doubleToConvert = Double.parseDouble(amountEditText.getText().toString());

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tsp, teaspoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.kg, kilogramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pint, pintTextView);

    }

    private void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit currentUnit,
                                             Quantity.Unit preferredUnit, TextView theTextView) {

        Quantity unitQuantity = new Quantity(doubleToConvert, currentUnit);

        String tempUnit = unitQuantity.to(preferredUnit).toString();

        theTextView.setText(tempUnit);
    }

}
