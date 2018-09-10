package com.example.iba.ibasecond;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.iba.ibasecond.Services.ServicesActivity;
import com.example.iba.ibasecond.courses_update.AddingCourseActivity;
import com.example.iba.ibasecond.courses_update.CoursesTypesActivity;
import com.example.iba.ibasecond.courses_update.UpdateCourseActivity;
import com.example.iba.ibasecond.courses_update.UpdateTypeActivity;
import com.example.iba.ibasecond.last_news.LastNewsActivity;
import com.example.iba.ibasecond.last_news.NewsUpdateActivity;
import com.example.iba.ibasecond.library.BooksActivity;
import com.example.iba.ibasecond.library.LibraryActivity;
import com.example.iba.ibasecond.library_update.AddingBookActivity;
import com.example.iba.ibasecond.library_update.LibraryContentActivity;
import com.example.iba.ibasecond.library_update.LibraryUpdateTypeActivity;
import com.example.iba.ibasecond.library_update.UpdateBookActivity;
import com.example.iba.ibasecond.payment_methods.PaymentMethodsActivity;
import com.example.iba.ibasecond.training.head_online_courses.HeadOrOnlineCoursesActivity;
import com.example.iba.ibasecond.training.TrainingActivity;
import com.example.iba.ibasecond.training.CoursesActivity;
import com.example.iba.ibasecond.training.tourism_courses.TourismCoursesActivity;
import com.example.iba.ibasecond.works_development.WorksDevelopmentActivity;

import java.util.Random;

public class HelperClass {

    Context context;

    private String facebookUrl = "https://www.facebook.com/iba.eg.usa/";
    private String twitterUrl = "https://twitter.com/iba_usa";
    private String linkedinUrl = "https://eg.linkedin.com/in/iba-eg-usa-71192016a?trk=profile-badge";

    public static String acd_logo = "https://firebasestorage.googleapis.com/v0/b/ibachat-77f29.appspot.com/o/icons%2Facd_logo.png?alt=media&token=f529f050-caf4-46c0-a431-6a290b91ce58";
    public static String adc_nav_logo = "https://firebasestorage.googleapis.com/v0/b/ibachat-77f29.appspot.com/o/icons%2Fadc_nav_logo.png?alt=media&token=cf5e64b2-2e17-42ad-add6-6ac1268c5efe";
    public static String ic_last_news = "https://firebasestorage.googleapis.com/v0/b/ibachat-77f29.appspot.com/o/icons%2Fic_last_news.png?alt=media&token=235bd5cf-9add-48bf-a215-0f32ac1db2d5";
    public static String ic_about_us = "https://firebasestorage.googleapis.com/v0/b/ibachat-77f29.appspot.com/o/icons%2Fic_about_us.png?alt=media&token=677fa062-0296-4513-ac95-f01c14d40d3c";
    public static String ic_library = "https://firebasestorage.googleapis.com/v0/b/ibachat-77f29.appspot.com/o/icons%2Fic_library.png?alt=media&token=f91a4e93-cca6-41f5-8e88-a878abe626a2";
    public static String ic_training = "https://firebasestorage.googleapis.com/v0/b/ibachat-77f29.appspot.com/o/icons%2Fic_training.png?alt=media&token=bf944128-3585-466d-b4b8-812d1a1a7d89";
    public static String ic_works_development = "https://firebasestorage.googleapis.com/v0/b/ibachat-77f29.appspot.com/o/icons%2Fic_works_development.png?alt=media&token=8a6b9800-37b2-4099-ad05-c460dd9a9eee";
    public static String ic_payment_method = "https://firebasestorage.googleapis.com/v0/b/ibachat-77f29.appspot.com/o/icons%2Fic_payment_method.png?alt=media&token=096fee32-7d9a-4f46-8bb3-17d13d7cf2a0";
    public static String ic_employees = "https://firebasestorage.googleapis.com/v0/b/ibachat-77f29.appspot.com/o/icons%2Fic_employees.png?alt=media&token=83b1e89b-3ab2-4849-9322-276e2a7b1fad";
    public static String ic_update = "https://firebasestorage.googleapis.com/v0/b/ibachat-77f29.appspot.com/o/icons%2Fic_update.png?alt=media&token=f57d392b-42ee-404b-a784-b4104f8d68f8";


    public static String COURSE_TYPE = "course_type";
    public static String COURSE_CATEGORY = "course_category";
    public static String COURSES_LIST = "courses_list";

    public static String LAST_NEWS = "أخر الأخبار";

    public static String LIBRARY = "المكتبة";
    public static String LIBRARY_CATEGORY = "library_category";
    public static String BOOK_OR_IMAGE = "book_or_image";
    public static String FREE_BOOKS = "كتب مجانية";
    public static String PAYED_BOOKS = "كتب مدفوعة";
    public static String IMAGES = "صور";
    public static String VIDEOS = "فيديوهات";

    public static String WORKS_DEVELOPMENT = "تطوير الأعمال";
    public static String SOCIAL_MEDIA_SERVICES = "خدمات السوشيال ميديا";
    public static String MULTIPLE_PACKAGES = "باقات متعددة";
    public static String PACKAGE_CATEGORY = "package_category";


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

    public void openPaymentMethodsActivity() {
        Intent intent = new Intent(context, PaymentMethodsActivity.class);
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

    public void openLastNewsActivity() {
        Intent intent = new Intent(context, LastNewsActivity.class);
        context.startActivity(intent);
    }

    public void openNewsUpdateActivity() {
        Intent intent = new Intent(context, NewsUpdateActivity.class);
        context.startActivity(intent);
    }

    public void openBooksActivity(String book_category) {
        Intent intent = new Intent(context, BooksActivity.class);
        intent.putExtra(LIBRARY_CATEGORY, book_category);
        context.startActivity(intent);
    }

    public void openAllUpdateActivity() {
        Intent intent = new Intent(context, AllUpdateActivity.class);
        context.startActivity(intent);
    }

    public void openLibraryContentActivity() {
        Intent intent = new Intent(context, LibraryContentActivity.class);
        context.startActivity(intent);
    }

    public void openLibraryUpdateTypeActivity(String library_category, String bookOrImage) {
        Intent intent = new Intent(context, LibraryUpdateTypeActivity.class);
        intent.putExtra(LIBRARY_CATEGORY, library_category);
        intent.putExtra(BOOK_OR_IMAGE, bookOrImage);
        context.startActivity(intent);
    }

    public void openAddingBookActivity() {
        Intent intent = new Intent(context, AddingBookActivity.class);
        context.startActivity(intent);
    }

    public void openUpdateBookActivity() {
        Intent intent = new Intent(context, UpdateBookActivity.class);
        context.startActivity(intent);
    }

    public void openServicesActivity() {
        Intent intent = new Intent(context, ServicesActivity.class);
        context.startActivity(intent);
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(15);
        char tempChar;
        for (int i = 0; i < randomLength; i++) {
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

}
