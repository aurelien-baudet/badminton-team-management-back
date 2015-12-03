package fr.aba.bad.compo.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.aba.bad.compo.core.exception.club.provider.ClubNotFoundForPlayer;
import fr.aba.bad.compo.core.exception.club.provider.ClubProviderException;
import fr.aba.bad.compo.core.exception.player.provider.PlayerInfoNotFoundException;
import fr.aba.bad.compo.core.exception.player.provider.PlayerNotFoundException;
import fr.aba.bad.compo.core.exception.player.provider.PlayerProviderException;
import fr.aba.bad.compo.core.exception.ranking.RankingException;
import fr.aba.bad.compo.web.dto.ErrorDTO;

@ControllerAdvice
public class ExceptionTranslator {
	@ExceptionHandler(PlayerProviderException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDTO handle(PlayerProviderException exception) {
		return new ErrorDTO(exception);
	}

	@ExceptionHandler(PlayerInfoNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO handle(PlayerInfoNotFoundException exception) {
		return new ErrorDTO(exception);
	}

	@ExceptionHandler(PlayerNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO handle(PlayerNotFoundException exception) {
		return new ErrorDTO(exception);
	}

	@ExceptionHandler(RankingException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDTO handle(RankingException exception) {
		return new ErrorDTO(exception);
	}

	@ExceptionHandler(ClubProviderException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDTO handle(ClubProviderException exception) {
		return new ErrorDTO(exception);
	}

	@ExceptionHandler(ClubNotFoundForPlayer.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO handle(ClubNotFoundForPlayer exception) {
		return new ErrorDTO(exception);
	}

}
