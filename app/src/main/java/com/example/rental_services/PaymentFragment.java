package com.example.rental_services;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rental_services.Database.BookingWithDetails;
import com.example.rental_services.Entities.Address;
import com.example.rental_services.Entities.Booking;
import com.example.rental_services.Entities.BookingInfo;
import com.example.rental_services.Entities.Item;
import com.example.rental_services.Entities.ItemInfo;
import com.example.rental_services.Entities.Payment;
import com.example.rental_services.Entities.Shipment;
import com.example.rental_services.Entities.User;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {

    ShapeableImageView card, cash, mobilepay;
    TextView paymentText, underTextpayment, cardtxt, cashtxt, mobilepaytxt;
    EditText cardOwner, cardNo, cardExpiry, cardthree;
    Button btn_payment;
    ConstraintLayout row;
    Booking booking;
    Payment payment;
    Shipment shipment;
    ItemInfo itemInfo;
    User user;
    Item item;
    Address address;
    BookingInfo bookingInfo;
    Animation animSlideDown, animSlideUp, animMove, fadeOut, fadeIn;
    ConfirmedBookingFragment confirmFragment;
    public PaymentFragment() {
        // Required empty public constructor
    }


    public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        findView(view);
        findAnimation();
        removeViews();
        animationBaseView();
        card.setOnClickListener(view1 -> {
            payment = new Payment(true, false, false);

            startAnimationCardClick();
        });
        btn_payment.setOnClickListener(v -> {
            try {
                goToLastFragment();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return view;
    }

    private void startAnimationCardClick(){
        mobilepay.startAnimation(animSlideUp);
        card.startAnimation(animSlideUp);
        cash.startAnimation(animSlideUp);
        cash.setVisibility(View.GONE);
        row.removeView(cash);
        btn_payment.setVisibility(View.VISIBLE);
        underTextpayment.startAnimation(animSlideUp);
        cardtxt.startAnimation(fadeOut);
        cardtxt.startAnimation(fadeOut);
        mobilepaytxt.startAnimation(fadeOut);
        cardOwner.setVisibility(View.VISIBLE);
        cardOwner.setAnimation(animSlideDown);
        cardthree.setVisibility(View.VISIBLE);
        cardthree.setAnimation(animSlideDown);
        cardExpiry.setVisibility(View.VISIBLE);
        cardExpiry.setAnimation(animSlideDown);
        cardNo.setVisibility(View.VISIBLE);
        cardNo.setAnimation(animSlideDown);
    }

    private void animationBaseView(){
        card.setVisibility(View.VISIBLE);
        card.startAnimation(animSlideDown);
        mobilepay.setVisibility(View.VISIBLE);
        mobilepay.startAnimation(animSlideDown);
        cash.setVisibility(View.VISIBLE);
        cash.startAnimation(animSlideDown);
        underTextpayment.setVisibility(View.VISIBLE);
        underTextpayment.startAnimation(animSlideDown);
        btn_payment.setVisibility(View.VISIBLE);

    }

    private void findView(View view){
        card = view.findViewById(R.id.mastercardimage);
        cash = view.findViewById(R.id.cashImage);
        mobilepay = view.findViewById(R.id.mobilePayimage);
        paymentText = view.findViewById(R.id.paymentText);
        underTextpayment = view.findViewById(R.id.paymentUnderText);
        cardOwner = view.findViewById(R.id.cardOwnerName);
        cardExpiry = view.findViewById(R.id.cardExpiry);
        cardthree = view.findViewById(R.id.cardthreeNumber);
        cardNo = view.findViewById(R.id.cardNumber);
        btn_payment = view.findViewById(R.id.btn_payment);
        row = view.findViewById(R.id.constraint_layout_payment);
        cardtxt = view.findViewById(R.id.mastercard);
        cashtxt = view.findViewById(R.id.cashOnDelivery);
        mobilepaytxt = view.findViewById(R.id.mobilepay);
    }
    private void findAnimation(){
        animSlideDown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
        animSlideUp = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
        animMove = AnimationUtils.loadAnimation(getContext(), R.anim.move_right);
        fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);

    }

    private void removeViews(){
        cardNo.setVisibility(View.GONE);
        cardthree.setVisibility(View.GONE);
        cardOwner.setVisibility(View.GONE);
        cardExpiry.setVisibility(View.GONE);
    }
    public void getData(){
        bookingInfo = (BookingInfo) getArguments().getSerializable("dateObj");
        shipment = (Shipment) getArguments().getSerializable("shipment");
        address = (Address) getArguments().getSerializable("address");
        user = ((BookingActivity)getActivity()).getUser();
        item = ((BookingActivity)getActivity()).getItem();
        itemInfo = new ItemInfo(false, true, bookingInfo.getToDate());
    }
    public void goToLastFragment() throws ExecutionException, InterruptedException {
//        (int userCreatorId, String rentalRules, String paymentRules, int booking_info_id, int itemId, int paymentId, int shipmentId)
        getData();
        String paymentMethod = getRightPayment(payment.isCreditCard(), payment.isCash(), payment.isMobilePay());
        String shipmentMethod = getRightShipment(shipment.isOwner_delivery(), shipment.isPick_up(), shipment.isShipmentByPost());
        booking = new Booking(user.getUserId(), "Take Care of this item as of your own", "Payment will be made after the item is returned", bookingInfo.getBooking_info_id(), item.getItemId(), payment.getPaymentId(), shipment.getShipmentId(), item.getUserCreatorId());
        BookingWithDetails bookingDetails = new BookingWithDetails(booking.getBookingId(), user.getUserId(), bookingInfo.getFromDate(),
                bookingInfo.getToDate(), item.getName(), item.getModel(), item.getBrand(), shipmentMethod,
                paymentMethod, "is Rented", user.getFullName(), String.valueOf(item.getPrice()), item.getImagepath());


        Bundle bundle = new Bundle();
        address.setUserCreatorId(user.getUserId());
        long bookingId = ((BookingActivity)getActivity()).insertAllBookingInfo(shipment, itemInfo, bookingInfo, payment, address, booking, bookingDetails);
//        ((BookingActivity)getActivity()).bookItem(booking);
        bundle.putSerializable("address", address);
        bundle.putSerializable("dateObj", bookingInfo);
        bundle.putSerializable("shipment", shipment);
        bundle.putSerializable("payment", payment);
        bundle.putSerializable("item", item);
        confirmFragment = new ConfirmedBookingFragment();
        confirmFragment.setArguments(bundle);
        FragmentManager manager = getParentFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentInside, confirmFragment);
        transaction.commit();
    }

    private String getRightPayment(boolean creditCard, boolean cash, boolean mobilePay) {
        String s;
        if (creditCard = true) {
            s = "Credit Card";
        } else if (cash = true) {
            s = "Cash";
        } else if (mobilePay = true) {
            s = "Mobilepay";
        } else {
            s = "Not Paid yet";
        }
        return s;
    }


    private String getRightShipment(boolean delivery, boolean pickup, boolean post){
        String s;
        if (delivery = true) {
            s = "Delivery";
        } else if (pickup = true) {
            s = "Pickup";
        } else if (post = true) {
            s = "Post";
        } else {
            s = "Not Chosen";
        }
        return s;
    }
    }



