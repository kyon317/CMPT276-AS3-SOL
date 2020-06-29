package ca.cmpt276.as3_minesweeper_sol;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

/**
 * "Congratulations, you won" dialog box.
 */
public class WonFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.won_layout, null);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.congratulations)
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }
}
