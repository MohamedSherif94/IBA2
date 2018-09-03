package com.example.iba.ibasecond;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.iba.ibasecond.Services.ServicesActivity;
import com.example.iba.ibasecond.courses_update.AddingCourseActivity;
import com.example.iba.ibasecond.courses_update.CoursesTypesActivity;
import com.example.iba.ibasecond.courses_update.UpdateCourseActivity;
import com.example.iba.ibasecond.courses_update.UpdateTypeActivity;
import com.example.iba.ibasecond.library.LibraryActivity;
import com.example.iba.ibasecond.training.head_online_courses.HeadOrOnlineCoursesActivity;
import com.example.iba.ibasecond.training.TrainingActivity;
import com.example.iba.ibasecond.training.CoursesActivity;
import com.example.iba.ibasecond.training.tourism_courses.TourismCoursesActivity;
import com.example.iba.ibasecond.works_development.WorksDevelopmentActivity;

public class HelperClass {

    Context context;

    private String facebookUrl = "https://www.facebook.com/iba.eg.usa/";
    private String twitterUrl = "https://twitter.com/iba_usa";
    private String linkedinUrl = "https://eg.linkedin.com/in/iba-eg-usa-71192016a?trk=profile-badge";

    public static String COURSE_TYPE = "course_type";
    public static String COURSE_CATEGORY = "course_category";
    public static String COURSES_LIST = "courses_list";


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

    public void openHeadOnlineCoursesActivity() {
        Intent intent = new Intent(context, HeadOrOnlineCoursesActivity.class);
        context.startActivity(intent);
    }

    public void openTourismCoursesActivity() {
        Intent intent = new Intent(context, TourismCoursesActivity.class);
        context.startActivity(intent);
    }

    public void openCoursesActivity(String course_type, String course_category) {
        Intent intent = new Intent(context, CoursesActivity.class);
        intent.putExtra(COURSE_TYPE, course_type);
        intent.putExtra(COURSE_CATEGORY, course_category);
        context.startActivity(intent);
    }

    public void openCoursesTypeActivity() {
        Intent intent = new Intent(context, CoursesTypesActivity.class);
        context.startActivity(intent);
    }

    public void openUpdateTypeActivity(String[] courses_list, String course_type) {
        Intent intent = new Intent(context, UpdateTypeActivity.class);
        intent.putExtra(COURSES_LIST, courses_list);
        intent.putExtra(COURSE_TYPE, course_type);
        context.startActivity(intent);
    }

    public void openAddingCourseActivity(String course_type, String course_category) {
        Intent intent = new Intent(context, AddingCourseActivity.class);
        intent.putExtra(COURSE_TYPE, course_type);
        intent.putExtra(COURSE_CATEGORY, course_category);
        context.startActivity(intent);
    }

    public void openUpdateCourseActivity(String course_type, String course_category) {
        Intent intent = new Intent(context, UpdateCourseActivity.class);
        intent.putExtra(COURSE_TYPE, course_type);
        intent.putExtra(COURSE_CATEGORY, course_category);
        context.startActivity(intent);
    }

    public void openLibraryActivity() {
        Intent intent = new Intent(context, LibraryActivity.class);
        context.startActivity(intent);
    }

    public void openServicesActivity() {
        Intent intent = new Intent(context, ServicesActivity.class);
        context.startActivity(intent);
    }


    public String getEnglishCourseCategory(String category) {

        switch (category){

            case "الاقتصاد":
                return "Economy";
            case "البنوك":
                return "Banks";
        }
        return null;
    }

}
