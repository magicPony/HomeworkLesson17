package com.example.taras.homeworklesson17.api;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.taras.homeworklesson17.MainActivity;
import com.example.taras.homeworklesson17.R;

/**
 * Created by taras on 15.04.16.
 */
public class EventHandler {

    private static Toast toast;

    public static void commitFragment(Fragment fragment, String fragmentTag) {
        MainActivity
                .getInstance()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rl_container_AM, fragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public static boolean isFragmentOnTheTop(String fragment) {
        int stackSize = MainActivity
                .getInstance()
                .getSupportFragmentManager()
                .getBackStackEntryCount();

        String topFragment = MainActivity
                .getInstance()
                .getSupportFragmentManager()
                .getBackStackEntryAt(stackSize - 1)
                .getName();

        return topFragment.equals(fragment);
    }

    public static void showToast(String message) {
        toast = Toast.makeText(MainActivity.getInstance(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
