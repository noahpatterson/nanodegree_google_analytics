/*
 * Copyright (C) 2015 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
 
package com.example.android.dinnerapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;


public class OrderDinnerActivity extends Activity {
    //String selectedDinnerExtrasKey = String.valueOf(R.string.selected_dinner);
    private String selectedDinner;
    private Product product;
    private Tracker gaTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_dinner);
    }

    protected void onStart() {
        super.onStart();

        // Set the heading
        TextView heading_tv = (TextView) findViewById(R.id.textView_info_heading);
        heading_tv.setText(getResources().getText(R.string.order_online_heading));

        // Set the text
        TextView tv = (TextView) findViewById(R.id.textView_info);

        String dinner = getIntent().getStringExtra("selectedDinner");
        selectedDinner = dinner;
        tv.setText("This is where you will order the selected dinner: \n\n" +
                dinner);
        sendViewProductHit(dinner);
    }

    private void sendViewProductHit(String dinner) {
        String dinnerId = Utility.getDinnerId(dinner);
        product = new Product()
                .setId(dinnerId)
                .setName("dinner")
                .setPrice(5)
                .setVariant(dinner)
                .setQuantity(1);

        ProductAction productAction = new ProductAction(ProductAction.ACTION_DETAIL);

        gaTracker = ((MyApplication) getApplication()).getTracker();

        gaTracker.send(new HitBuilders.EventBuilder()
                .setCategory("shopping steps")
                .setAction("View order dinner screen")
                .setLabel(dinner)
                .addProduct(product)
                .setProductAction(productAction)
                .build());
    }

    private void sendAddDintterToCartHit() {
        ProductAction productAction = new ProductAction(ProductAction.ACTION_ADD);
        gaTracker.send(new HitBuilders.EventBuilder()
                .setCategory("shopping steps")
                .setAction("add dinner to cart")
                .setLabel(selectedDinner)
                .addProduct(product)
                .setProductAction(productAction)
                .build());
    }

    public void addDinnerToCart(View view) {
        sendAddDintterToCartHit();
        Utility.showMyToast("added " + selectedDinner + " to cart",this);
        return;
    }

}
