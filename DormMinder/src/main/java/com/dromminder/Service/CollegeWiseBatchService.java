package com.dromminder.Service;

import com.dromminder.dto.Response;
import com.dromminder.model.CollegeWiseBatch;

public interface CollegeWiseBatchService {

	Response<?> save(CollegeWiseBatch collegeWiseBatch);

	Response<?> getAllByCollegeId(Integer collegeId);

}
