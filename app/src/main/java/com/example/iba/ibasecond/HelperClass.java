package com.example.iba.ibasecond;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.iba.ibasecond.Services.ServicesActivity;
import com.example.iba.ibasecond.courses_update.AddingCourseActivity;
import com.example.iba.ibasecond.courses_update.CoursesTypesActivity;
import com.example.iba.ibasecond.courses_update.UpdateCourseActivity;
import com.example.iba.ibasecond.courses_update.UpdateTypeActivity;
import com.example.iba.ibasecond.training.HeadOrOnlineTrainingActivity;
import com.example.iba.ibasecond.training.TrainingActivity;
import com.example.iba.ibasecond.training.head_online_training_courses.EconomyCoursesActivity;
import com.example.iba.ibasecond.works_development.WorksDevelopmentActivity;

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

    public void openWorksDevelopmentActivity() {
        Intent intent = new Intent(context, WorksDevelopmentActivity.class);
        context.startActivity(intent);
    }

    public void openTrainingActivity() {
        Intent intent = new Intent(context, TrainingActivity.class);
        context.startActivity(intent);
    }

    public void openHeadOnlineTrainingActivity() {
        Intent intent = new Intent(context, HeadOrOnlineTrainingActivity.class);
        context.startActivity(intent);
    }

    public void openEconomyCoursesActivity() {
        Intent intent = new Intent(context, EconomyCoursesActivity.class);
        context.startActivity(intent);
    }

    public void openCoursesTypeActivity() {
        Intent intent = new Intent(context, CoursesTypesActivity.class);
        context.startActivity(intent);
    }

    public void openUpdateTypeActivity(String[] courses_list) {
        Intent intent = new Intent(context, UpdateTypeActivity.class);
        intent.putExtra("courses_list", courses_list);
        context.startActivity(intent);
    }

    public void openAddingCourseActivity(String course_category) {
        Intent intent = new Intent(context, AddingCourseActivity.class);
        intent.putExtra("course_category", course_category);
        context.startActivity(intent);
    }

    public void openUpdateCourseActivity() {
        Intent intent = new Intent(context, UpdateCourseActivity.class);
        context.startActivity(intent);
    }

    public void openServicesActivity() {
        Intent intent = new Intent(context, ServicesActivity.class);
        context.startActivity(intent);
    }



}
