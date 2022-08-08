package com_hch_exam_make.interceptor;

import com_hch_exam_make.Rq;

public interface Interceptor {
  boolean run(Rq rq);
}
