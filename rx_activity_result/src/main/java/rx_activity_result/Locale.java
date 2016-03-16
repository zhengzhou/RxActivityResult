package rx_activity_result;

/**
 * Created by victor on 16/03/16.
 */
public interface Locale {
    String RX_ACTIVITY_RESULT_NOT_REGISTER = "You must call RxActivityResult.register(application) before attempting to use startIntent";
    String ACTIVITY_MISMATCH_TARGET_UI = "Mismatch target ui: the current Activity is not the same class type as the one provided when calling RxActivityResult.on(this)";
    String FRAGMENT_MISMATCH_TARGET_UI = "Mismatch target ui: not found any active Fragment whose class matches with the one provided when calling RxActivityResult.on(this)";
}
