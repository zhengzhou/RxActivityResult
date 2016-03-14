package rx_activity_result;

import java.io.Serializable;

interface OnResult extends Serializable {
    void response(Result result);
}
