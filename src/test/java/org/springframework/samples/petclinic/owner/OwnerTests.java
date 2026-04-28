/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Owner}.
 */
class OwnerTests {

	private Owner owner;

	@BeforeEach
	void setUp() {
		owner = new Owner();
		owner.setFirstName("George");
		owner.setLastName("Franklin");
		owner.setAddress("110 W. Liberty St.");
		owner.setCity("Madison");
		owner.setTelephone("6085551023");
	}

	@Test
	void addPetNewPetIsAdded() {
		Pet pet = new Pet();
		assertThat(pet.isNew()).isTrue();
		owner.addPet(pet);
		assertThat(owner.getPets()).hasSize(1);
	}

	@Test
	void addPetExistingPetIsNotAdded() {
		Pet pet = new Pet();
		pet.setId(1);
		assertThat(pet.isNew()).isFalse();
		owner.addPet(pet);
		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void getPetByNameFound() {
		Pet pet = new Pet();
		pet.setName("Max");
		owner.addPet(pet);
		assertThat(owner.getPet("Max")).isEqualTo(pet);
	}

	@Test
	void getPetByNameCaseInsensitive() {
		Pet pet = new Pet();
		pet.setName("Max");
		owner.addPet(pet);
		assertThat(owner.getPet("max")).isEqualTo(pet);
		assertThat(owner.getPet("MAX")).isEqualTo(pet);
	}

	@Test
	void getPetByNameNotFound() {
		assertThat(owner.getPet("Unknown")).isNull();
	}

	@Test
	void getPetByNameIgnoreNewTrue() {
		// A pet with no id is "new" — ignoreNew=true should exclude it
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(pet.isNew()).isTrue();
		assertThat(owner.getPet("Buddy", true)).isNull();
	}

	@Test
	void getPetByNameIgnoreNewFalse() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		owner.addPet(pet);
		assertThat(owner.getPet("Buddy", false)).isEqualTo(pet);
	}

	@Test
	void getPetByNameIgnoreNewTrueWithExistingPet() {
		Pet pet = new Pet();
		pet.setName("Buddy");
		pet.setId(42);
		// Must use internal list since addPet only adds new pets
		owner.getPets().add(pet);
		assertThat(owner.getPet("Buddy", true)).isEqualTo(pet);
	}

	@Test
	void getPetByIdFound() {
		Pet pet = new Pet();
		pet.setId(5);
		pet.setName("Whiskers");
		owner.getPets().add(pet);
		assertThat(owner.getPet(5)).isEqualTo(pet);
	}

	@Test
	void getPetByIdNotFound() {
		Pet pet = new Pet();
		pet.setId(5);
		owner.getPets().add(pet);
		assertThat(owner.getPet(99)).isNull();
	}

	@Test
	void getPetByIdSkipsNewPets() {
		// New pets (id=null) are skipped when searching by id, so looking up by any id
		// that no existing pet has returns null
		Pet pet = new Pet();
		pet.setName("NewPet");
		owner.addPet(pet);
		assertThat(owner.getPet((Integer) 99)).isNull();
	}

	@Test
	void addVisitAddsToCorrectPet() {
		Pet pet = new Pet();
		pet.setId(7);
		pet.setName("Leo");
		owner.getPets().add(pet);

		Visit visit = new Visit();
		visit.setDescription("Annual check-up");
		owner.addVisit(7, visit);

		assertThat(pet.getVisits()).hasSize(1);
		assertThat(pet.getVisits().iterator().next().getDescription()).isEqualTo("Annual check-up");
	}

	@Test
	void addVisitWithNullPetIdThrowsException() {
		assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(null, new Visit()))
			.withMessageContaining("Pet identifier must not be null");
	}

	@Test
	void addVisitWithNullVisitThrowsException() {
		assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(1, null))
			.withMessageContaining("Visit must not be null");
	}

	@Test
	void addVisitWithInvalidPetIdThrowsException() {
		assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(999, new Visit()))
			.withMessageContaining("Invalid Pet identifier");
	}

	@Test
	void toStringContainsRelevantFields() {
		owner.setId(1);
		String result = owner.toString();
		assertThat(result).contains("Franklin");
		assertThat(result).contains("George");
		assertThat(result).contains("Madison");
	}

	@Test
	void gettersAndSetters() {
		assertThat(owner.getFirstName()).isEqualTo("George");
		assertThat(owner.getLastName()).isEqualTo("Franklin");
		assertThat(owner.getAddress()).isEqualTo("110 W. Liberty St.");
		assertThat(owner.getCity()).isEqualTo("Madison");
		assertThat(owner.getTelephone()).isEqualTo("6085551023");
	}

}
