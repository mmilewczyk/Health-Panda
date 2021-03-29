package com.agiklo.HeathProject.service.workout;

import com.agiklo.HeathProject.mapper.SetMapper;
import com.agiklo.HeathProject.model.dto.SetDTO;
import com.agiklo.HeathProject.model.workout.Exercise;
import com.agiklo.HeathProject.model.workout.Set;
import com.agiklo.HeathProject.repository.ExerciseRepository;
import com.agiklo.HeathProject.repository.SetRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SetService {

    private final SetRepository setRepository;
    private final ExerciseRepository exerciseRepository;
    private final SetMapper setMapper;

    public List<SetDTO> getAllSets(){
        return setRepository.findAll()
                .stream()
                .map(setMapper::mapSetToDTO)
                .collect(Collectors.toList());
    }

    public List<SetDTO> getAllByExercises(Long id) {
        return setRepository.getAllByExercise_ExerciseId(id)
                .stream()
                .map(setMapper::mapSetToDTO)
                .collect(Collectors.toList());
    }

    public Set addNewSet(Long id, Set set){
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot found specify Exercise"));
        set.setExercise(exercise);
        return setRepository.save(set);
    }

    public void deleteSetById(Long id, Principal principal) {
        Set set = setRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Set does not exist"));
        if (principal.getName().equals(set.getExercise().getWorkout().getUser().getEmail())){
            setRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not owner of this set");
        }
    }
}
