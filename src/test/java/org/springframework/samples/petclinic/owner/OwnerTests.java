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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * Unit tests for the {@link Owner} domain class.
 */
class OwnerTests {

	private Owner owner;

	private Pet savedPet;

	private Pet newPet;

	@BeforeEach
	void setUp() {
		owner = new Owner();
		owner.setFirstName("George");
		owner.setLastName("Franklin");
		owner.setAddress("110 W. Liberty St.");
		owner.setCity("Madison");
		owner.setTelephone("6085551023");

		savedPet = new Pet();
		savedPet.setId(1);
		savedPet.setName("Max");
		savedPet.setBirthDate(LocalDate.of(2020, 1, 1));

		newPet = new Pet();
		newPet.setName("Buddy");
	}

	// --- addPet ---

	@Test
	void addPetAddsPetWhenNew() {
		owner.addPet(newPet);
		assertThat(owner.getPets()).contains(newPet);
	}

	@Test
	void addPetIgnoresPetWhenNotNew() {
		// savedPet has an ID set so it is not new
		owner.addPet(savedPet);
		assertThat(owner.getPets()).doesNotContain(savedPet);
	}

	// --- getPet(String) ---

	@Test
	void getPetByNameReturnsMatchingPet() {
		owner.addPet(newPet);
		Pet found = owner.getPet("Buddy");
		assertThat(found).isSameAs(newPet);
	}

	@Test
	void getPetByNameIsCaseInsensitive() {
		owner.addPet(newPet);
		assertThat(owner.getPet("buddy")).isSameAs(newPet);
		assertThat(owner.getPet("BUDDY")).isSameAs(newPet);
	}

	@Test
	void getPetByNameReturnsNullWhenNotFound() {
		owner.addPet(newPet);
		assertThat(owner.getPet("Unknown")).isNull();
	}

	// --- getPet(String, boolean ignoreNew) ---

	@Test
	void getPetByNameIgnoreNewReturnsPetThatIsSaved() {
		owner.getPets().add(savedPet); // add saved pet directly (bypassing addPet filter)
		Pet found = owner.getPet("Max", true);
		assertThat(found).isSameAs(savedPet);
	}

	@Test
	void getPetByNameIgnoreNewReturnsNullForUnsavedPet() {
		owner.addPet(newPet);
		assertThat(owner.getPet("Buddy", true)).isNull();
	}

	@Test
	void getPetByNameNotIgnoreNewReturnsUnsavedPet() {
		owner.addPet(newPet);
		assertThat(owner.getPet("Buddy", false)).isSameAs(newPet);
	}

	// --- getPet(Integer id) ---

	@Test
	void getPetByIdReturnsSavedPet() {
		owner.getPets().add(savedPet);
		assertThat(owner.getPet(1)).isSameAs(savedPet);
	}

	@Test
	void getPetByIdReturnsNullWhenNotFound() {
		owner.getPets().add(savedPet);
		assertThat(owner.getPet(99)).isNull();
	}

	@Test
	void getPetByIdIgnoresNewPets() {
		// newPet has no id, so getPet(Integer) must not return it even when id matches
		// null
		owner.addPet(newPet);
		assertThat(owner.getPet((Integer) null)).isNull();
	}

	// --- addVisit ---

	@Test
	void addVisitAttachesVisitToCorrectPet() {
		owner.getPets().add(savedPet);
		Visit visit = new Visit();
		visit.setDescription("check-up");
		owner.addVisit(savedPet.getId(), visit);
		assertThat(savedPet.getVisits()).contains(visit);
	}

	@Test
	void addVisitThrowsWhenPetIdIsNull() {
		assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(null, new Visit()))
			.withMessageContaining("Pet identifier must not be null");
	}

	@Test
	void addVisitThrowsWhenVisitIsNull() {
		owner.getPets().add(savedPet);
		assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(savedPet.getId(), null))
			.withMessageContaining("Visit must not be null");
	}

	@Test
	void addVisitThrowsWhenPetIdNotFound() {
		assertThatIllegalArgumentException().isThrownBy(() -> owner.addVisit(999, new Visit()))
			.withMessageContaining("Invalid Pet identifier");
	}

	// --- toString ---

	@Test
	void toStringContainsOwnerDetails() {
		String s = owner.toString();
		assertThat(s).contains("George").contains("Franklin").contains("Madison");
	}

}
