package com.example.iba.ibasecond;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.iba.ibasecond.Services.ServicesActivity;

public class HelperClass {

    Context context;

    private String facebookUrl = "https://www.facebook.com/iba.eg.usa/";
    private String twitterUrl = "https://twitter.com/iba_usa";
    private String linkedinUrl = "https://eg.linkedin.com/in/iba-eg-usa-71192016a?trk=profile-badge";


    public HelperClass(Context context) {
        this.context = context;
    }

    public void openFacebookPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
        context.startActivity(intent);

    }

    public void openTwitterPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
        context.startActivity(intent);
    }

    public void openLinkedInPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl));
        context.startActivity(intent);
    }

    public void openLoginActivity() {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void openChatHomeActivity() {
        Intent intent = new Intent(context, HomeChatActivity.class);
        context.startActivity(intent);
    }

    public void openServicesActivity() {
        Intent intent = new Intent(context, ServicesActivity.class);
        context.startActivity(intent);
    }

}
