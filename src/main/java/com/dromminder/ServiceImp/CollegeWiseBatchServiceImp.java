package com.dromminder.ServiceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dromminder.Service.CollegeWiseBatchService;
import com.dromminder.Util.Validation;
import com.dromminder.dto.CollegeWiseBatchDto;
import com.dromminder.dto.Response;
import com.dromminder.model.CollegeWiseBatch;
import com.dromminder.repository.CollegeWiseBatchRepository;

@Service
public class CollegeWiseBatchServiceImp implements CollegeWiseBatchService {

	@Autowired
	private CollegeWiseBatchRepository collegeWiseBatchRepository;

	@Override
	public Response<?> save(CollegeWiseBatch collegeWiseBatch) {

		try {

			Response<?> vallidateCollegeWiseBatchSave = Validation.vallidateCollegeWiseBatchSave(collegeWiseBatch);

			if (vallidateCollegeWiseBatchSave != null) {
				return vallidateCollegeWiseBatchSave;
			}
			CollegeWiseBatch findByCollegeIdAndName = collegeWiseBatchRepository
					.findByCollegeIdAndName(collegeWiseBatch.getCollegeId(), collegeWiseBatch.getName());
			if (findByCollegeIdAndName != null) {
				return new Response<>(collegeWiseBatch.getName() + "Already Exit", null, 400);
			}

			CollegeWiseBatch batch = new CollegeWiseBatch();
			batch.setName(collegeWiseBatch.getName());
			batch.setCollegeId(collegeWiseBatch.getCollegeId());
			batch.setUpdatedOn(new Date());
			batch.setCreatedOn(new Date());
			batch.setIsActive(true);
			collegeWiseBatchRepository.save(batch);

			return new Response<>("Save Successfully", null, 200);
		} catch (Exception e) {
			return new Response<>("Something Went wrong ", null, 400);
		}
	}

	@Override
	public Response<?> getAllByCollegeId(Integer collegeId) {

		if (collegeId == null) {
			return new Response<>("Provide valid college id ", null, 400);
		}
		try {
			List<CollegeWiseBatchDto> collect = new ArrayList<>();
			List<CollegeWiseBatch> findAllByCollegeId = collegeWiseBatchRepository.findAllByCollegeId(collegeId);
			if (findAllByCollegeId != null) {
				collect = findAllByCollegeId.stream().map(CollegeWiseBatchDto::fromEntity).collect(Collectors.toList());
			}

			return new Response<>("Success ", collect, 200);
		} catch (Exception e) {

			return new Response<>("Something Went wrong ", null, 400);

		}
	}

	@Override
	public Response<?> updateCollegeWiseBatch(CollegeWiseBatch collegeWiseBatch) {

		try {
			if (collegeWiseBatch.getId() == null) {
				return new Response<>("Batch id required", null, 400);
			}

			Optional<CollegeWiseBatch> findById = collegeWiseBatchRepository.findById(collegeWiseBatch.getId());

			if (findById.isEmpty()) {
				return new Response<>("Provide Valid id", null, 400);
			}
			findById.get().setIsActive(false);

			collegeWiseBatchRepository.save(findById.get());

			return new Response<>("Status Update Succesfully", null, 400);
		} catch (Exception e) {
			return new Response<>("Something Went wrong ", null, 400);
		}
	}

}
