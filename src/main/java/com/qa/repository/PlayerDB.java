package com.qa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.model.Player;

	
@Transactional(value = TxType.SUPPORTS)
public class PlayerDB implements PlayerRepository {
	
	@PersistenceContext(unitName = "myPU")
	private EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)			 //TRANSACTION
	public Player create(Player player) {
		em.persist(player);
		return player;
	}
	
	public Player read(int id) {		//find id
		Player player = em.find(Player.class, id);
		return player;
	}
	
	public List<Player> readAll(){		//find all
		TypedQuery<Player> q = em.createQuery("Select plyr from Player plyr", Player.class);
		List<Player> list = q.getResultList();
		return list;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public Player update(int id, Player newInfo) {
		Player player = read(id);
		player.setForename(newInfo.getForename());
		player.setSurname(newInfo.getSurname());
		player.setContactNo(newInfo.getContactNo());
		player.setEmail(newInfo.getEmail());
		player.setEmergencyContact(newInfo.getEmergencyContact());
		player.setEmergencyContactNo(newInfo.getEmergencyContactNo());
		player.setTeamId(newInfo.getTeamId());
		return player;		
	}
	
	@Transactional(value = TxType.REQUIRED)
	public void delete(int id) {
		em.remove(read(id));
	}

}
