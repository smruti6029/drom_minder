package com.dromminder.ServiceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dromminder.Service.StateService;
import com.dromminder.dto.Response;
import com.dromminder.dto.StateDto;
import com.dromminder.model.State;
import com.dromminder.repository.StateRepository;

@Service
public class StateServiceImp implements StateService {

	@Autowired
	private StateRepository stateRepository;

	@Override
	public Response<?> getAllstate() {
		List<State> allstate = stateRepository.findAll();

		List<StateDto> states = new ArrayList<>();

		allstate.forEach(x -> {
			StateDto statedto = StateDto.convertEntityToDTO(x);
			states.add(statedto);
		});
		return new Response<>("Success to fetch State", states, 200);

	}
}
